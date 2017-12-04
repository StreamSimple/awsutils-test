package com.streamsimple.awsutilstest;

import com.streamsimple.awsutils.S3Utils;
import java.io.File;

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
