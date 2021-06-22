/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package privateca;

// [START privateca_disable_ca]

import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.CertificateAuthority.State;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.DisableCertificateAuthorityRequest;
import com.google.longrunning.Operation;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DisableCertificateAuthority {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    // location: For a list of locations, see: certificate-authority-service/docs/locations
    // caPoolName: The name of the CA pool under which the CA is present.
    // certificateAuthority: The name of the CA to be disabled.
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "ca-pool-name";
    String certificateAuthority = "certificate-authority-name";
    disableCertificateAuthority(project, location, caPoolName, certificateAuthority);
  }

  // Disable a Certificate Authority which is present in the given CA pool.
  public static void disableCertificateAuthority(
      String project, String location, String caPoolName, String certificateAuthority)
      throws IOException, ExecutionException, InterruptedException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the `certificateAuthorityServiceClient.close()` method on the client to safely
    // clean up any remaining background resources.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {

      // Create the Certificate Authority Name.
      CertificateAuthorityName certificateAuthorityName =
          CertificateAuthorityName.newBuilder()
              .setProject(project)
              .setLocation(location)
              .setCaPool(caPoolName)
              .setCertificateAuthority(certificateAuthority)
              .build();

      // Create the Disable Certificate Authority Request.
      DisableCertificateAuthorityRequest disableCertificateAuthorityRequest =
          DisableCertificateAuthorityRequest.newBuilder()
              .setName(certificateAuthorityName.toString())
              .build();

      // Disable the Certificate Authority.
      ApiFuture<Operation> futureCall =
          certificateAuthorityServiceClient
              .disableCertificateAuthorityCallable()
              .futureCall(disableCertificateAuthorityRequest);
      Operation response = futureCall.get();

      if (response.hasError()) {
        System.out.println("Error while disabling Certificate Authority !");
        return;
      }

      // Get the current CA state.
      State caState =
          certificateAuthorityServiceClient
              .getCertificateAuthority(certificateAuthorityName)
              .getState();

      // Check if the Certificate Authority is disabled.
      if (caState == State.DISABLED) {
        System.out.println("Disabled Certificate Authority : " + certificateAuthority);
      } else {
        System.out.println(
            "Cannot disable the Certificate Authority ! Current CA State: " + caState);
      }
    }
  }
}
// [END privateca_disable_ca]
