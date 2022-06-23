/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.security.privateca.v1.samples;

// [START privateca_v1_generated_certificateauthorityserviceclient_revokecertificate_sync]
import com.google.cloud.security.privateca.v1.Certificate;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.CertificateName;
import com.google.cloud.security.privateca.v1.RevocationReason;
import com.google.cloud.security.privateca.v1.RevokeCertificateRequest;

public class SyncRevokeCertificate {

  public static void main(String[] args) throws Exception {
    syncRevokeCertificate();
  }

  public static void syncRevokeCertificate() throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {
      RevokeCertificateRequest request =
          RevokeCertificateRequest.newBuilder()
              .setName(
                  CertificateName.of("[PROJECT]", "[LOCATION]", "[CA_POOL]", "[CERTIFICATE]")
                      .toString())
              .setReason(RevocationReason.forNumber(0))
              .setRequestId("requestId693933066")
              .build();
      Certificate response = certificateAuthorityServiceClient.revokeCertificate(request);
    }
  }
}
// [END privateca_v1_generated_certificateauthorityserviceclient_revokecertificate_sync]
