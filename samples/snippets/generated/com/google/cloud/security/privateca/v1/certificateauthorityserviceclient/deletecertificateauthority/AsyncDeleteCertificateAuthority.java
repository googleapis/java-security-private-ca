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

// [START privateca_v1_generated_certificateauthorityserviceclient_deletecertificateauthority_async]
import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.DeleteCertificateAuthorityRequest;
import com.google.longrunning.Operation;

public class AsyncDeleteCertificateAuthority {

  public static void main(String[] args) throws Exception {
    asyncDeleteCertificateAuthority();
  }

  public static void asyncDeleteCertificateAuthority() throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {
      DeleteCertificateAuthorityRequest request =
          DeleteCertificateAuthorityRequest.newBuilder()
              .setName(
                  CertificateAuthorityName.of(
                          "[PROJECT]", "[LOCATION]", "[CA_POOL]", "[CERTIFICATE_AUTHORITY]")
                      .toString())
              .setRequestId("requestId693933066")
              .setIgnoreActiveCertificates(true)
              .setSkipGracePeriod(true)
              .build();
      ApiFuture<Operation> future =
          certificateAuthorityServiceClient
              .deleteCertificateAuthorityCallable()
              .futureCall(request);
      // Do something.
      Operation response = future.get();
    }
  }
}
// [END privateca_v1_generated_certificateauthorityserviceclient_deletecertificateauthority_async]
