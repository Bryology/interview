provider "aws" {
  region = "us-east-1"
}

resource "aws_iam_role" "iam_for_lambda" {
  name               = "interview_iam_for_lambda"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "lambda_policy" {
  name   = "interview_lambda_policy"
  role   = aws_iam_role.iam_for_lambda.id
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*"
    }
  ]
}
EOF
}

resource "aws_lambda_function" "interview_lambda" {
  filename         = "../target/scala-2.13/deere-interview.jar"
  role             = aws_iam_role.iam_for_lambda.arn
  function_name    = "deere-interview"
  memory_size      = 512
  runtime          = "java8"
  handler          = "deere.InterviewHandler"
  source_code_hash = base64sha256(filebase64("../target/scala-2.13/deere-interview.jar"))
  timeout          = 60
}