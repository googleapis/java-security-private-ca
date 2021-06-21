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

import com.google.cloud.security.privateca.v1.CertificateAuthority;
import com.google.cloud.security.privateca.v1.CertificateAuthority.State;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DisableCertificateAuthority {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "your-ca-pool-name";
    String certificateAuthority = "certificate-authority";
    disableCertificateAuthority(project, location, caPoolName, certificateAuthority);
  }

  public static void disableCertificateAuthority(String project, String location, String caPoolName,
      String certificateAuthority) throws IOException, ExecutionException, InterruptedException {
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {

      CertificateAuthorityName certificateAuthorityName = CertificateAuthorityName.newBuilder()
          .setProject(project)
          .setLocation(location)
          .setCaPool(caPoolName)
          .setCertificateAuthority(certificateAuthority)
          .build();

      CertificateAuthority certificateAuthorityResponse = certificateAuthorityServiceClient
          .disableCertificateAuthorityAsync(certificateAuthorityName).get();
      if (certificateAuthorityResponse.getState() == State.DISABLED) {
        System.out.println("Disabled Certificate Authority : " + certificateAuthority);
      } else {
        System.out.println("Cannot disable the Certificate Authority ! ! Please try again ! !");
      }
    }
  }

}
