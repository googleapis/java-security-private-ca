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

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.security.privateca.v1.CaPool;
import com.google.cloud.security.privateca.v1.CaPoolName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.OperationMetadata;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DeleteCAPool {


  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException, TimeoutException {
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "your-ca-pool-name";
    deleteCAPool(project, location, caPoolName);
  }

  public static void deleteCAPool(String project, String location, String caPoolName)
      throws InterruptedException, ExecutionException, IOException, TimeoutException {
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {

      CaPoolName caPool = CaPoolName.newBuilder()
          .setProject(project)
          .setLocation(location)
          .setCaPool(caPoolName).build();

      OperationFuture<CaPool, OperationMetadata> response = certificateAuthorityServiceClient
          .deleteCaPoolAsync(caPool);
      long startTime = System.currentTimeMillis();
      while ((response.peekMetadata() == null || !response.isDone())
          && System.currentTimeMillis() - startTime <= 180000) {
        TimeUnit.SECONDS.sleep(5);
      }
      System.out.println("Deleted CA Pool: " + caPoolName);
    }
  }

}
