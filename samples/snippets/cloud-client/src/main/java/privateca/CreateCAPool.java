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

import com.google.cloud.security.privateca.v1.CaPool;
import com.google.cloud.security.privateca.v1.CaPool.Tier;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.CreateCaPoolRequest;
import com.google.cloud.security.privateca.v1.LocationName;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CreateCAPool {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException {
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "your-ca-pool-name";
    createCAPool(project, location, caPoolName);
  }

  // Create a Certificate Authority Pool. All certificates created under this ca pool will
  // follow the same issuance policy, IAM policies,etc.,
  public static void createCAPool(String project, String location, String caPoolName)
      throws InterruptedException, ExecutionException, IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the `certificateAuthorityServiceClient.close()` method on the client to safely
    // clean up any remaining background resources.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient =
        CertificateAuthorityServiceClient.create()) {

      /* Create a pool request as below:
         Set Parent which denotes the project id and
         location (see, certificate-authority-service/docs/locations) of the ca pool.
         Set a unique name for the ca pool.
         Set the Tier (see, certificate-authority-service/docs/tiers).
       */
      CreateCaPoolRequest caPoolRequest = CreateCaPoolRequest.newBuilder()
          .setParent(LocationName.of(project, location).toString())
          .setCaPoolId(caPoolName)
          .setCaPool(CaPool.newBuilder()
              .setTier(Tier.ENTERPRISE)
              .build())
          .build();

      // Create the CA pool
      CaPool caPoolResponse = certificateAuthorityServiceClient.createCaPoolAsync(caPoolRequest)
          .get();

      // Check if the CA Pool is created.
      System.out.println("CA pool created successfully: " + caPoolResponse.getName());
    }
  }
}
