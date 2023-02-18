import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import ec2 = require('aws-cdk-lib/aws-ec2');
import * as ecs_patterns from "aws-cdk-lib/aws-ecs-patterns";
import * as ecs from "aws-cdk-lib/aws-ecs";

export class AwsDefaultVpcFmdbStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);
    
    //stack = MyStack(app, "MyStack", env=Environment(account="account_id", region="region"))
    
    
    
    const existingVpc = ec2.Vpc.fromLookup(this, 'ExistingVPC',{isDefault: true, vpcId: 'vpc-00b2afb4239d54531'});
    
    const existingSubnet1 = ec2.Subnet.fromSubnetId(this, 'ExistingSubnet1', 'subnet-0c6d8578c51c0a6a7');
    
    const existingSubnet2 = ec2.Subnet.fromSubnetId(this, 'ExistingSubnet2', 'subnet-0559579e56a9b448c');
    
    const existingSubnet3 = ec2.Subnet.fromSubnetId(this, 'ExistingSubnet3', 'subnet-0337597298dd6387a');
    
    const clusterMyPayara = new ecs.Cluster(this, "myfmdbcluster", {
      vpc: existingVpc
    });
    
    const loadBalancedFargateServiceMyPayara = new ecs_patterns.ApplicationLoadBalancedFargateService(this, "myfmdbfargate", {
      cluster: clusterMyPayara, // Required
      assignPublicIp: false, 
      cpu: 256, // Default is 256
      desiredCount: 1, // Default is 1
      taskImageOptions: { image: ecs.ContainerImage.fromAsset("./MyFMDB"), containerPort: 8080, 
      environment: {
          JDBC_SETTING:"jdbc:postgresql://databaseHostnameHere:5432/fmdb_db?currentSchema=fmdb", 
          PG_USER: "fmdb_proxy_user", 
          PG_PASSWORD: "admin"},  },
      
      taskSubnets: {
        subnets: [existingSubnet1,existingSubnet2,existingSubnet3]
      },
      memoryLimitMiB: 1024, // Default is 512
      publicLoadBalancer: true, // Default is true
      healthCheckGracePeriod: cdk.Duration.minutes(3)
    });
    
    loadBalancedFargateServiceMyPayara.targetGroup.configureHealthCheck({
      port: "8080"
    });
    
    const scalableTargetCustomMyPayara = loadBalancedFargateServiceMyPayara.service.autoScaleTaskCount({
      minCapacity: 1,
      maxCapacity: 1,
    });
    
    scalableTargetCustomMyPayara.scaleOnCpuUtilization('CpuScaling', {
      targetUtilizationPercent: 50,
    });
    
     scalableTargetCustomMyPayara.scaleOnMemoryUtilization('MemoryScaling', {
      targetUtilizationPercent: 50,
    });
    
  }
}
