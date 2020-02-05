# About the application
This is a sample application that publishes messages to AWS SNS. It is assumed that the SNS is configured
to trigger the `sns-listener-lambda` function. The `sns-listener-lambda` function gets an SNS event,
parses it and puts it into the SQS.

## Steps to Run the Application
1. Download the source code `aws-commons` under the `aws` repository because the maven modules in this
project depends on it.

1. Create an SQS from the AWS Console and get its ARN

1. Update the SQS arn in the `SnsTestTopicHandler.java` class

1. Create an SNS topic from the AWS Console and get its ARN

1. Update the SNS arn in the `TestSnsProvider.java` class 

1. Build the `aws-commons` with maven with `mvn install` command to have that artifact under .m2

1. Build the `sns-lambda-sqs` parent project with `mvn package`

1. Create a lambda function from AWS console with Java 8 Runtime and upload the 
`jar-with-dependencies` file under the `sns-listener-lambda/target`

1. Set the handler info of your lambda function to `com.fd.aws.lambda.SnsTestTopicHandler::handleRequest`

1. Add an SNS trigger to your lambda function or add Lambda function subscription to your SNS topic.
These two have the same effect.

1. Run the main method of `TestSnsProvider.java` and monitor your SQS. You should see your message in the 
queue

