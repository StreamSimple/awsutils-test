/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsimple.awsutilstest;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * This class is used to retrieve environmental variables with AWS credentials.
 */
public final class AWSUtils
{
  public static final String ENV_AWS_REGION = "AWS_REGION";
  public static final String ENV_AWS_ACCESS_KEY = "AWS_ACCESS_KEY";
  public static final String ENV_AWS_SECRET_KEY = "AWS_SECRET_KEY";

  private AWSUtils()
  {
  }

  public static String getAWSRegion()
  {
    final String region = System.getenv(ENV_AWS_REGION);

    if (region == null) {
      final String message = String.format("The %s environment variable is not set.", ENV_AWS_REGION);
      throw new NullPointerException(message);
    }

    return region;
  }

  public static String getAWSAccessKey()
  {
    final String accessKey = System.getenv(ENV_AWS_ACCESS_KEY);

    if (accessKey == null) {
      final String message = String.format("The %s environment variable is not set.", ENV_AWS_ACCESS_KEY);
      throw new NullPointerException(message);
    }

    return accessKey;
  }

  public static String getAWSSecretKey()
  {
    final String secretKey = System.getenv(ENV_AWS_SECRET_KEY);

    if (secretKey == null) {
      final String message = String.format("The %s environment variable is not set.", ENV_AWS_SECRET_KEY);
      throw new NullPointerException(message);
    }

    return secretKey;
  }

  public static AWSCredentials getCredentials()
  {
    final String accessKey = getAWSAccessKey();
    final String secretKey = getAWSSecretKey();

    return new BasicAWSCredentials(accessKey, secretKey);
  }

  public static AWSCredentialsProvider getCredentialsProvider()
  {
    final AWSCredentials credentials = getCredentials();

    return new AWSStaticCredentialsProvider(credentials);
  }
}
