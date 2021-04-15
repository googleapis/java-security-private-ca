/*
 * Copyright 2020 Google LLC
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
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/security/privateca/v1/resources.proto

package com.google.cloud.security.privateca.v1;

public interface CaPoolOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.cloud.security.privateca.v1.CaPool)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * Output only. The resource name for this [CaPool][google.cloud.security.privateca.v1.CaPool] in the
   * format `projects/&#42;&#47;locations/&#42;&#47;caPools/&#42;`.
   * </pre>
   *
   * <code>string name = 1 [(.google.api.field_behavior) = OUTPUT_ONLY];</code>
   *
   * @return The name.
   */
  java.lang.String getName();
  /**
   *
   *
   * <pre>
   * Output only. The resource name for this [CaPool][google.cloud.security.privateca.v1.CaPool] in the
   * format `projects/&#42;&#47;locations/&#42;&#47;caPools/&#42;`.
   * </pre>
   *
   * <code>string name = 1 [(.google.api.field_behavior) = OUTPUT_ONLY];</code>
   *
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString getNameBytes();

  /**
   *
   *
   * <pre>
   * Required. Immutable. The [Tier][google.cloud.security.privateca.v1.CaPool.Tier] of this [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.Tier tier = 2 [(.google.api.field_behavior) = REQUIRED, (.google.api.field_behavior) = IMMUTABLE];
   * </code>
   *
   * @return The enum numeric value on the wire for tier.
   */
  int getTierValue();
  /**
   *
   *
   * <pre>
   * Required. Immutable. The [Tier][google.cloud.security.privateca.v1.CaPool.Tier] of this [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.Tier tier = 2 [(.google.api.field_behavior) = REQUIRED, (.google.api.field_behavior) = IMMUTABLE];
   * </code>
   *
   * @return The tier.
   */
  com.google.cloud.security.privateca.v1.CaPool.Tier getTier();

  /**
   *
   *
   * <pre>
   * Optional. The [IssuancePolicy][google.cloud.security.privateca.v1.CaPool.IssuancePolicy] to control how [Certificates][google.cloud.security.privateca.v1.Certificate]
   * will be issued from this [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.IssuancePolicy issuance_policy = 3 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   *
   * @return Whether the issuancePolicy field is set.
   */
  boolean hasIssuancePolicy();
  /**
   *
   *
   * <pre>
   * Optional. The [IssuancePolicy][google.cloud.security.privateca.v1.CaPool.IssuancePolicy] to control how [Certificates][google.cloud.security.privateca.v1.Certificate]
   * will be issued from this [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.IssuancePolicy issuance_policy = 3 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   *
   * @return The issuancePolicy.
   */
  com.google.cloud.security.privateca.v1.CaPool.IssuancePolicy getIssuancePolicy();
  /**
   *
   *
   * <pre>
   * Optional. The [IssuancePolicy][google.cloud.security.privateca.v1.CaPool.IssuancePolicy] to control how [Certificates][google.cloud.security.privateca.v1.Certificate]
   * will be issued from this [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.IssuancePolicy issuance_policy = 3 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   */
  com.google.cloud.security.privateca.v1.CaPool.IssuancePolicyOrBuilder
      getIssuancePolicyOrBuilder();

  /**
   *
   *
   * <pre>
   * Optional. The [PublishingOptions][google.cloud.security.privateca.v1.CaPool.PublishingOptions] to follow when issuing
   * [Certificates][google.cloud.security.privateca.v1.Certificate] from any [CertificateAuthority][google.cloud.security.privateca.v1.CertificateAuthority] in this
   * [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.PublishingOptions publishing_options = 4 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   *
   * @return Whether the publishingOptions field is set.
   */
  boolean hasPublishingOptions();
  /**
   *
   *
   * <pre>
   * Optional. The [PublishingOptions][google.cloud.security.privateca.v1.CaPool.PublishingOptions] to follow when issuing
   * [Certificates][google.cloud.security.privateca.v1.Certificate] from any [CertificateAuthority][google.cloud.security.privateca.v1.CertificateAuthority] in this
   * [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.PublishingOptions publishing_options = 4 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   *
   * @return The publishingOptions.
   */
  com.google.cloud.security.privateca.v1.CaPool.PublishingOptions getPublishingOptions();
  /**
   *
   *
   * <pre>
   * Optional. The [PublishingOptions][google.cloud.security.privateca.v1.CaPool.PublishingOptions] to follow when issuing
   * [Certificates][google.cloud.security.privateca.v1.Certificate] from any [CertificateAuthority][google.cloud.security.privateca.v1.CertificateAuthority] in this
   * [CaPool][google.cloud.security.privateca.v1.CaPool].
   * </pre>
   *
   * <code>
   * .google.cloud.security.privateca.v1.CaPool.PublishingOptions publishing_options = 4 [(.google.api.field_behavior) = OPTIONAL];
   * </code>
   */
  com.google.cloud.security.privateca.v1.CaPool.PublishingOptionsOrBuilder
      getPublishingOptionsOrBuilder();

  /**
   *
   *
   * <pre>
   * Optional. Labels with user-defined metadata.
   * </pre>
   *
   * <code>map&lt;string, string&gt; labels = 5 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  int getLabelsCount();
  /**
   *
   *
   * <pre>
   * Optional. Labels with user-defined metadata.
   * </pre>
   *
   * <code>map&lt;string, string&gt; labels = 5 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  boolean containsLabels(java.lang.String key);
  /** Use {@link #getLabelsMap()} instead. */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String> getLabels();
  /**
   *
   *
   * <pre>
   * Optional. Labels with user-defined metadata.
   * </pre>
   *
   * <code>map&lt;string, string&gt; labels = 5 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  java.util.Map<java.lang.String, java.lang.String> getLabelsMap();
  /**
   *
   *
   * <pre>
   * Optional. Labels with user-defined metadata.
   * </pre>
   *
   * <code>map&lt;string, string&gt; labels = 5 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  java.lang.String getLabelsOrDefault(java.lang.String key, java.lang.String defaultValue);
  /**
   *
   *
   * <pre>
   * Optional. Labels with user-defined metadata.
   * </pre>
   *
   * <code>map&lt;string, string&gt; labels = 5 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  java.lang.String getLabelsOrThrow(java.lang.String key);
}
