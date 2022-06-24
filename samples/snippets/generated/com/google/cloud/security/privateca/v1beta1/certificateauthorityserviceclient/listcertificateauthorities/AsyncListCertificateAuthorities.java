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

package com.google.cloud.security.privateca.v1beta1.samples;

// [START
// privateca_v1beta1_generated_certificateauthorityserviceclient_listcertificateauthorities_async]
import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1beta1.CertificateAuthority;
import com.google.cloud.security.privateca.v1beta1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1beta1.ListCertificateAuthoritiesRequest;
import com.google.cloud.security.privateca.v1beta1.LocationName;

public class AsyncListCertificateAuthorities {

  public static void main(String[] args) throws Exception {
    asyncListCertificateAuthorities();
  }

  public static void asyncListCertificateAuthorities() throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {
      ListCertificateAuthoritiesRequest request =
          ListCertificateAuthoritiesRequest.newBuilder()
              .setParent(LocationName.of("[PROJECT]", "[LOCATION]").toString())
              .setPageSize(883849137)
              .setPageToken("pageToken873572522")
              .setFilter("filter-1274492040")
              .setOrderBy("orderBy-1207110587")
              .build();
      ApiFuture<CertificateAuthority> future =
          certificateAuthorityServiceClient
              .listCertificateAuthoritiesPagedCallable()
              .futureCall(request);
      // Do something.
      for (CertificateAuthority element : future.get().iterateAll()) {
        // doThingsWith(element);
      }
    }
  }
}
// [END
// privateca_v1beta1_generated_certificateauthorityserviceclient_listcertificateauthorities_async]
