const { execSync } = require('child_process');

const tag = process.env.INPUT_TAG;

// Fetch the latest tag
execSync('git fetch --tags');

// Checkout the tag
execSync(`git checkout ${tag}`);

// Commit data to master branch
execSync('git checkout -B master');
execSync('git merge --ff-only $GIT_COMMIT');
