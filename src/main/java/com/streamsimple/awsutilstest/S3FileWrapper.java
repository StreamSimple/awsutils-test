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

import java.io.File;

import com.streamsimple.awsutils.S3Utils;

public class S3FileWrapper
{
  private String awsAccessKey;
  private String awsSecretKey;
  private String region;
  private String bucket;
  private String objectName;
  private File localFile;

  private S3FileWrapper(String awsAccessKey,
      String awsSecretKey,
      String region,
      String bucket,
      String objectName,
      File localFile)
  {
    this.awsAccessKey = awsAccessKey;
    this.awsSecretKey = awsSecretKey;
    this.region = region;
    this.bucket = bucket;
    this.objectName = objectName;
    this.localFile = localFile;
  }

  public S3FileWrapper run(Test test)
  {
    S3Utils.putFile(awsAccessKey, awsSecretKey, region, bucket, objectName, localFile);

    try {
      test.test();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    S3Utils.removeFile(awsAccessKey, awsSecretKey, region, bucket, objectName);
    return this;
  }

  public static class Builder
  {
    private String awsAccessKey;
    private String awsSecretKey;
    private String region;
    private String bucket;
    private String objectName;
    private File localFile;

    public Builder()
    {
    }

    public Builder setAwsAccessKey(final String awsAccessKey)
    {
      if (awsAccessKey == null) {
        throw new NullPointerException();
      }

      this.awsAccessKey = awsAccessKey;
      return this;
    }

    public Builder setAwsSecretKey(final String awsSecretKey)
    {
      if (awsSecretKey == null) {
        throw new NullPointerException();
      }

      this.awsSecretKey = awsSecretKey;
      return this;
    }

    public Builder setRegion(final String region)
    {
      if (region == null) {
        throw new NullPointerException();
      }

      this.region = region;
      return this;
    }

    public Builder setBucket(final String bucket)
    {
      if (bucket == null) {
        throw new NullPointerException();
      }

      this.bucket = bucket;
      return this;
    }

    public Builder setObjectName(final String objectName)
    {
      if (objectName == null) {
        throw new NullPointerException();
      }

      this.objectName = objectName;
      return this;
    }

    public Builder setLocalFile(final File localFile)
    {
      if (localFile == null) {
        throw new NullPointerException();
      }

      this.localFile = localFile;
      return this;
    }

    public S3FileWrapper build()
    {
      return new S3FileWrapper(awsAccessKey, awsSecretKey, region, bucket, objectName, localFile);
    }
  }

  public interface Test
  {
    void test() throws Exception;
  }
}
