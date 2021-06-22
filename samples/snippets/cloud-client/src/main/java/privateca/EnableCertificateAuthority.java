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

// [START privateca_enable_ca]

import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.CertificateAuthority.State;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.EnableCertificateAuthorityRequest;
import com.google.longrunning.Operation;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class EnableCertificateAuthority {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    // location: For a list of locations, see: certificate-authority-service/docs/locations
    // caPoolName: The name of the CA pool under which the CA is present.
    // certificateAuthority: The name of the CA to be enabled.
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "ca-pool-name";
    String certificateAuthority = "certificate-authority-name";
    enableCertificateAuthority(project, location, caPoolName, certificateAuthority);
  }

  // Enable the Certificate Authority present in the given ca pool.
  // CA cannot be enabled if it has been already deleted.
  public static void enableCertificateAuthority(String project, String location, String caPoolName,
      String certificateAuthority)
      throws IOException, ExecutionException, InterruptedException {
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {
      // Create the Certificate Authority Name.
      CertificateAuthorityName certificateAuthorityName = CertificateAuthorityName.newBuilder()
          .setProject(project)
          .setLocation(location)
          .setCaPool(caPoolName)
          .setCertificateAuthority(certificateAuthority)
          .build();

      // Create the Enable Certificate Authority Request.
      EnableCertificateAuthorityRequest enableCertificateAuthorityRequest =
          EnableCertificateAuthorityRequest.newBuilder()
              .setName(certificateAuthorityName.toString()).build();

      // Enable the Certificate Authority.
      ApiFuture<Operation> futureCall = certificateAuthorityServiceClient
          .enableCertificateAuthorityCallable().futureCall(enableCertificateAuthorityRequest);
      Operation response = futureCall.get();

      if (response.hasError()) {
        System.out.println("Error while enabling Certificate Authority !");
        return;
      }

      // Get the current CA state.
      State caState = certificateAuthorityServiceClient
          .getCertificateAuthority(certificateAuthorityName)
          .getState();

      // Check if the CA is enabled.
      if (caState == State.ENABLED) {
        System.out.println("Enabled Certificate Authority : " + certificateAuthority);
      } else {
        System.out
            .println("Cannot enable the Certificate Authority ! Current CA State: " + caState);
      }
    }
  }
}
// [END privateca_enable_ca]
