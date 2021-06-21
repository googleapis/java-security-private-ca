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

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import com.google.cloud.security.privateca.v1.CaPoolName;
import com.google.cloud.security.privateca.v1.CertificateAuthority;
import com.google.cloud.security.privateca.v1.CertificateAuthorityName;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SnippetsIT {

  private static final String PROJECT_ID = System.getenv("GOOGLE_CLOUD_PROJECT");
  private static String LOCATION;
  private static String CA_POOL_NAME;
  private static String CA_POOL_NAME_DELETE;
  private static String CA_NAME;
  private static String CA_NAME_DELETE;

  private ByteArrayOutputStream stdOut;

  public static void reqEnvVar(String envVarName) {
    assertWithMessage(String.format("Missing environment variable '%s' ", envVarName))
        .that(System.getenv(envVarName)).isNotEmpty();
  }

  @BeforeClass
  public static void setUp() throws InterruptedException, ExecutionException, IOException {
    reqEnvVar("GOOGLE_APPLICATION_CREDENTIALS");
    reqEnvVar("GOOGLE_CLOUD_PROJECT");

    LOCATION = "asia-south1";
    CA_POOL_NAME = "ca-pool-" + UUID.randomUUID().toString();
    CA_POOL_NAME_DELETE = "ca-pool-" + UUID.randomUUID().toString();
    CA_NAME = "ca-name-" + UUID.randomUUID().toString();
    CA_NAME_DELETE = "ca-name-" + UUID.randomUUID().toString();

    privateca.CreateCAPool.createCAPool(PROJECT_ID, LOCATION, CA_POOL_NAME);
    privateca.CreateCAPool.createCAPool(PROJECT_ID, LOCATION, CA_POOL_NAME_DELETE);

    privateca.CreateCertificateAuthority
        .createCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME);
    privateca.CreateCertificateAuthority
        .createCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME_DELETE);
  }

  @AfterClass
  public static void cleanUp()
      throws InterruptedException, ExecutionException, IOException, TimeoutException {
    ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(stdOut));

    privateca.DeleteCertificateAuthority
        .deleteCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME);
    TimeUnit.SECONDS.sleep(5);
    privateca.DeleteCAPool.deleteCAPool(PROJECT_ID, LOCATION, CA_POOL_NAME);

    stdOut = null;
    System.setOut(null);
  }

  @Before
  public void beforeEach() {
    stdOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(stdOut));
  }

  @After
  public void afterEach() {
    stdOut = null;
    System.setOut(null);
  }

  @Test
  public void testCreateCAPool() throws IOException {
    // Check if the CA pool created during setup is successful
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {
      String caPoolName = certificateAuthorityServiceClient
          .getCaPool(CaPoolName.of(PROJECT_ID, LOCATION, CA_POOL_NAME).toString()).getName();
      assertThat(caPoolName).contains(
          String.format("projects/%s/locations/%s/caPools/%s", PROJECT_ID, LOCATION, CA_POOL_NAME));
    }
  }

  @Test
  public void testListCAPools() throws IOException {
    privateca.ListCAPools.listCAPools(PROJECT_ID, LOCATION);
    assertThat(stdOut.toString()).contains(CA_POOL_NAME);
  }

  @Test
  public void testDeleteCAPool()
      throws InterruptedException, ExecutionException, IOException, TimeoutException {
    privateca.DeleteCAPool.deleteCAPool(PROJECT_ID, LOCATION, CA_POOL_NAME_DELETE);
    assertThat(stdOut.toString()).contains("Deleted CA Pool: " + CA_POOL_NAME_DELETE);
  }

  @Test
  public void testCreateCertificateAuthority()
      throws InterruptedException, ExecutionException, IOException {
    try (CertificateAuthorityServiceClient certificateAuthorityServiceClient = CertificateAuthorityServiceClient
        .create()) {
      CertificateAuthority response = certificateAuthorityServiceClient.getCertificateAuthority(
          CertificateAuthorityName.of(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME).toString());
      assertThat(response.getName()).contains(CA_NAME);
    }
  }

  @Test
  public void testListCertificateAuthorities() throws IOException {
    privateca.ListCertificateAuthorities
        .listCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME);
    assertThat(stdOut.toString()).contains(CA_NAME);
  }

  @Test
  public void testEnableDisableCertificateAuthority()
      throws InterruptedException, ExecutionException, IOException {
    privateca.EnableCertificateAuthority
        .enableCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME);
    assertThat(stdOut.toString()).contains("Enabled Certificate Authority : " + CA_NAME);
    privateca.DisableCertificateAuthority
        .disableCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME);
    assertThat(stdOut.toString()).contains("Disabled Certificate Authority : " + CA_NAME);
  }

  @Test
  public void testDeleteCertificateAuthority()
      throws InterruptedException, ExecutionException, IOException {
    privateca.DeleteCertificateAuthority
        .deleteCertificateAuthority(PROJECT_ID, LOCATION, CA_POOL_NAME, CA_NAME_DELETE);
    assertThat(stdOut.toString())
        .contains("Successfully deleted Certificate Authority : " + CA_NAME_DELETE);
  }

}
