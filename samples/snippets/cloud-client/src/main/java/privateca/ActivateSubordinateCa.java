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

// [START privateca_activate_subordinateca]

import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.ActivateCertificateAuthorityRequest;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.SubordinateConfig;
import com.google.cloud.security.privateca.v1.SubordinateConfig.SubordinateConfigChain;
import com.google.longrunning.Operation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ActivateSubordinateCa {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    // TODO(developer): Replace these variables before running the sample.

    // location: For a list of locations, see:
    // https://cloud.google.com/certificate-authority-service/docs/locations
    // pool_Id: Set a unique id for the CA pool.
    // certificateAuthorityName: The name of the certificate authority which signed the CSR.
    // subordinateCaName: The CA to be activated.
    // pemCACertificate: The signed certificate, obtained by signing the CSR.
    // issuerCertificateChain: The certificate chain of the CA which signed the CSR.
    String project = "your-project-id";
    String location = "ca-location";
    String pool_Id = "ca-pool-id";
    String certificateAuthorityName = "certificate-authority-name";
    String subordinateCaName = "subordinate-certificate-authority-name";

    String pemCACertificate =
        "-----BEGIN CERTIFICATE-----\n" + "sample-pem-certificate\n" + "-----END CERTIFICATE-----";

    List<String> issuerCertificateChain = new ArrayList<>();

    activateSubordinateCA(
        project,
        location,
        pool_Id,
        certificateAuthorityName,
        subordinateCaName,
        pemCACertificate,
        issuerCertificateChain);
  }

  // Activate a subordinate CA.
  // Prerequisite: Get the CSR of the subordinate CA signed by another CA. Pass in the signed
  // certificate and the issuer CA's Certificate chain.
  // Post: After activating the subordinate CA, it should be enabled before issuing certificates.
  public static void activateSubordinateCA(
      String project,
      String location,
      String pool_Id,
      String certificateAuthorityName,
      String subordinateCaName,
      String pemCACertificate,
      List<String> issuerCertificateChain)
      throws ExecutionException, InterruptedException, IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the `certificateAuthorityServiceClient.close()` method on the client to safely
    // clean up any remaining background resources.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {
      // Subordinate CA parent.
      String certificateAuthorityParent =
          CertificateAuthorityName.of(project, location, pool_Id, subordinateCaName).toString();

      // Create the Activate CA Request.
      ActivateCertificateAuthorityRequest activateCertificateAuthorityRequest =
          ActivateCertificateAuthorityRequest.newBuilder()
              .setName(certificateAuthorityParent)
              // The signed certificate.
              .setPemCaCertificate(pemCACertificate)
              .setSubordinateConfig(
                  SubordinateConfig.newBuilder()
                      // The CA which signed the CSR.
                      .setCertificateAuthority(certificateAuthorityName)
                      // The certificate chain of the CA (which signed the CSR) from leaf to root.
                      .setPemIssuerChain(
                          SubordinateConfigChain.newBuilder()
                              .addAllPemCertificates(issuerCertificateChain)
                              .build())
                      .build())
              .build();

      // Activate the CA.
      ApiFuture<Operation> futureCall =
          certificateAuthorityServiceClient
              .activateCertificateAuthorityCallable()
              .futureCall(activateCertificateAuthorityRequest);

      Operation response = futureCall.get();

      if (response.hasError()) {
        System.out.println("Error while activating the subordinate CA! " + response.getError());
        return;
      }

      System.out.println(
          "Subordinate Certificate Authority activated successfully ! !" + subordinateCaName);
      TimeUnit.SECONDS.sleep(3);
      // The current state will be STAGED.
      // The Subordinate CA has to be ENABLED before issuing certificates.
      System.out.println(
          "Current State: "
              + certificateAuthorityServiceClient
                  .getCertificateAuthority(certificateAuthorityParent)
                  .getState());
    }
  }
}
// [END privateca_activate_subordinateca]
