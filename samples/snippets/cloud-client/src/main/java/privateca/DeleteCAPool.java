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
// [START privateca_delete_ca_pool]

import com.google.api.core.ApiFuture;
import com.google.cloud.security.privateca.v1.CaPoolName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.DeleteCaPoolRequest;
import com.google.longrunning.Operation;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class DeleteCAPool {


  public static void main(String[] args)
      throws InterruptedException, ExecutionException, IOException, TimeoutException {
    // location: For a list of locations, see: certificate-authority-service/docs/locations
    // caPoolName: The name of the CA pool to be deleted.
    String project = "your-project-id";
    String location = "ca-location";
    String caPoolName = "ca-pool-name";
    deleteCAPool(project, location, caPoolName);
  }

  // Delete the CA pool as mentioned by the caPoolName.
  // Before deleting the pool, all CAs in the pool MUST BE deleted.
  public static void deleteCAPool(String project, String location, String caPoolName)
      throws InterruptedException, ExecutionException, IOException {
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {

      // Set the project, location and caPoolName to delete.
      CaPoolName caPool = CaPoolName.newBuilder()
          .setProject(project)
          .setLocation(location)
          .setCaPool(caPoolName).build();

      // Create the Delete request.
      DeleteCaPoolRequest deleteCaPoolRequest = DeleteCaPoolRequest.newBuilder()
          .setName(caPool.toString()).build();

      // Delete the CA Pool.
      ApiFuture<Operation> futureCall = certificateAuthorityServiceClient
          .deleteCaPoolCallable().futureCall(deleteCaPoolRequest);
      Operation response = futureCall.get();

      if (response.hasError()) {
        System.out.println("Error while deleting CA pool !");
        return;
      }

      System.out.println("Deleted CA Pool: " + caPoolName);
    }
  }
}
// [END privateca_delete_ca_pool]
