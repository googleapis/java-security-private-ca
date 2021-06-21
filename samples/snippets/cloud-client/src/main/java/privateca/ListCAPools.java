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
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.LocationName;
import java.io.IOException;

public class ListCAPools {


  public static void main(String[] args) throws IOException {
    String project = "your-project-id";
    String location = "ca-location";
    listCAPools(project, location);
  }


  // List all CA pools present in the given project and location.
  public static void listCAPools(String project, String location) throws IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the `certificateAuthorityServiceClient.close()` method on the client to safely
    // clean up any remaining background resources.
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {

      LocationName locationName = LocationName.newBuilder()
          .setProject(project)
          .setLocation(location).build();

      String caPoolName = "";
      System.out.println("Available CA pools : ");
      for (CaPool caPool : certificateAuthorityServiceClient.listCaPools(locationName)
          .iterateAll()) {
        caPoolName = caPool.getName();
        System.out.println(
            caPoolName.substring(caPoolName.lastIndexOf("/") + 1) + " " + caPool.isInitialized());
      }
    }
  }

}
