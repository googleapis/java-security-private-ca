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

// [START privateca_create_ca]

import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.CaPoolName;
import com.google.cloud.security.privateca.v1.CertificateAuthority;
import com.google.cloud.security.privateca.v1.CertificateAuthority.KeyVersionSpec;
import com.google.cloud.security.privateca.v1.CertificateAuthority.SignHashAlgorithm;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.CertificateConfig;
import com.google.cloud.security.privateca.v1.CertificateConfig.SubjectConfig;
import com.google.cloud.security.privateca.v1.CreateCertificateAuthorityRequest;
import com.google.cloud.security.privateca.v1.KeyUsage;
import com.google.cloud.security.privateca.v1.KeyUsage.KeyUsageOptions;
import com.google.cloud.security.privateca.v1.Subject;
import com.google.cloud.security.privateca.v1.X509Parameters;
import com.google.cloud.security.privateca.v1.X509Parameters.CaOptions;
import com.google.longrunning.Operation;
import com.google.protobuf.Duration;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CreateCertificateAuthority {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    // TODO(developer): Replace these variables before running the sample.
    // location: For a list of locations, see:
    // https://cloud.google.com/certificate-authority-service/docs/locations
    // caPoolName: Set it to the CA Pool under which the CA should be created.
    // certificateAuthorityName: Unique name for the CA.
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "ca-pool-name";
    String certificateAuthorityName = "certificate-authority-name";
    createCertificateAuthority(project, location, caPoolName, certificateAuthorityName);
  }

  // Create Certificate Authority which is the root CA in the given CA Pool. This CA will be
  // responsible for signing certificates within this pool.
  public static void createCertificateAuthority(
      String project, String location, String caPoolName, String certificateAuthorityName)
      throws InterruptedException, ExecutionException, IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the `certificateAuthorityServiceClient.close()` method on the client to safely
    // clean up any remaining background resources.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {

      String commonName = "common-name";
      String orgName = "org-name";
      int caDuration = 100000; // Validity of this CA in seconds.

      // Set the types of Algorithm used to create a cloud KMS key.
      KeyVersionSpec keyVersionSpec =
          KeyVersionSpec.newBuilder().setAlgorithm(SignHashAlgorithm.RSA_PKCS1_4096_SHA256).build();

      // Set CA subject config.
      SubjectConfig subjectConfig =
          SubjectConfig.newBuilder()
              .setSubject(
                  Subject.newBuilder().setCommonName(commonName).setOrganization(orgName).build())
              .build();

      //  Set the key usage options for X.509 fields.
      X509Parameters x509Parameters =
          X509Parameters.newBuilder()
              .setKeyUsage(
                  KeyUsage.newBuilder()
                      .setBaseKeyUsage(
                          KeyUsageOptions.newBuilder().setCrlSign(true).setCertSign(true).build())
                      .build())
              .setCaOptions(CaOptions.newBuilder().setIsCa(true).build())
              .build();

      // Set certificate authority settings.
      CertificateAuthority certificateAuthority =
          CertificateAuthority.newBuilder()
              // CertificateAuthority.Type.SELF_SIGNED denotes that this CA is a root CA.
              .setType(CertificateAuthority.Type.SELF_SIGNED)
              .setKeySpec(keyVersionSpec)
              .setConfig(
                  CertificateConfig.newBuilder()
                      .setSubjectConfig(subjectConfig)
                      .setX509Config(x509Parameters)
                      .build())
              // Set the CA validity duration.
              .setLifetime(Duration.newBuilder().setSeconds(caDuration).build())
              .build();

      // Create the CertificateAuthorityRequest.
      CreateCertificateAuthorityRequest certificateAuthorityRequest =
          CreateCertificateAuthorityRequest.newBuilder()
              .setParent(CaPoolName.of(project, location, caPoolName).toString())
              .setCertificateAuthorityId(certificateAuthorityName)
              .setCertificateAuthority(certificateAuthority)
              .build();

      // Create Certificate Authority.
      ApiFuture<Operation> futureCall =
          certificateAuthorityServiceClient
              .createCertificateAuthorityCallable()
              .futureCall(certificateAuthorityRequest);
      Operation response = futureCall.get();

      if (response.hasError()) {
        System.out.println("Error while creating CA !" + response.getError());
        return;
      }

      System.out.println(
          "Certificate Authority created successfully : " + certificateAuthorityName);
    }
  }
}
// [END privateca_create_ca]
