package com.streamsimple.awsutilstest;

/**
 * This class is used to retrieve environmental variables with AWS credentials.
 */
public final class AWSUtils
{
  public static final String ENV_AWS_REGION = "AWS_REGION";
  public static final String ENV_AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
  public static final String ENV_AWS_SECRET_KEY = "AWS_SECRET_KEY";

  private AWSUtils() {
  }

  public static String getAWSRegion()
  {
    return System.getenv(ENV_AWS_REGION);
  }

  public static String getAWSAccessKey()
  {
    return System.getenv(ENV_AWS_ACCESS_KEY);
  }

  public static String getAWSSecretKey()
  {
    return System.getenv(ENV_AWS_SECRET_KEY);
  }
}
