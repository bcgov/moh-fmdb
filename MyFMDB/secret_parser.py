import boto3
import os
from botocore.exceptions import ClientError

def main():
    #secret_name = "dev/fmdb"
    #region_name = "ca-central-1"
    
    # Create a Secrets Manager client
    #session = boto3.session.Session()
    #client = session.client(
        #service_name='secretsmanager',
        #region_name=region_name)
        
    #try:
        #get_secret_value_response = client.get_secret_value(
            #SecretId=secret_name)
            
    #except ClientError as e:
        #raise e
        
    # Decrypts secret using the associated KMS key.    
    #secret_string = get_secret_value_response['SecretString']
    
    #Parses secret string for the secret
    #secret = ((secret_string.split(":"))[1].split("\""))[1]
    
    #Build and run asadmin command with the parsed secret
    #command = "asadmin create jvm-options \"-Dpreboot.command=asadmin --user admin --password " + secret + " create-password-alias database-password\""
        command = "asadmin create jvm-options \"-Dpreboot.command=asadmin --user admin --password postgres create-password-alias database-password\""
    os.system(command)

if __name__ == "__main__":
    main()