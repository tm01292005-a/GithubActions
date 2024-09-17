import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;

public class AwsAssumeRole {
	public static String assumeRole(String accessKeyId, String secretAccessKey, String roleArn, String roleSessionName) {
		StsClient stsClient = StsClient.builder()
				.credentialsProvider(
						StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
				.build();

		AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder().roleArn(roleArn)
				.roleSessionName(roleSessionName).build();

		AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);
		String id = assumeRoleResponse.credentials().accessKeyId();
		String secret = assumeRoleResponse.credentials().secretAccessKey();
		String token = assumeRoleResponse.credentials().sessionToken();
		return "<id>" + id + "</id>" + "<secret>" + secret + "</secret>" + "<token>" + token + "</token>";
	}
}