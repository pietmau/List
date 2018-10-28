package com.amazonaws.lambda.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class LambdaFunctionHandler implements RequestHandler<S3Event, String> {

    //private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public LambdaFunctionHandler() {}

    // Test purpose only.
    LambdaFunctionHandler(AmazonS3 s3) {
      //  this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
//        context.getLogger().log("Received event: " + event);
        
        String url = "jdbc:mysql://checklistappdatabase.cqe6ft379bmn.eu-west-1.rds.amazonaws.com:3306/";
        String userName = "admin";
        String password = "444gatti";
        //String dbName = "checklistappdatabase";
        String driver = "com.mysql.jdbc.Driver";
        try {
			Connection connection = DriverManager.getConnection(url, userName, password);
			return "Connected";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return e1.getLocalizedMessage();
		}
       

        // Get the object from the event and show its content type
//        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
//        String key = event.getRecords().get(0).getS3().getObject().getKey();
//        try {
//            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
//            String contentType = response.getObjectMetadata().getContentType();
//            context.getLogger().log("CONTENT TYPE: " + contentType);
//            return contentType;
//        } catch (Exception e) {
//            e.printStackTrace();
//            context.getLogger().log(String.format(
//                "Error getting object %s from bucket %s. Make sure they exist and"
//                + " your bucket is in the same region as this function.", key, bucket));
//            throw e;
//        }
    }
}