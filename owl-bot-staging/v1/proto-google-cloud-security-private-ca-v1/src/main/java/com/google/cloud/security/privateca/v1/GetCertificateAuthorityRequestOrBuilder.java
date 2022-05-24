// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/security/privateca/v1/service.proto

package com.google.cloud.security.privateca.v1;

public interface GetCertificateAuthorityRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.security.privateca.v1.GetCertificateAuthorityRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Required. The [name][google.cloud.security.privateca.v1.CertificateAuthority.name] of the [CertificateAuthority][google.cloud.security.privateca.v1.CertificateAuthority] to
   * get.
   * </pre>
   *
   * <code>string name = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * Required. The [name][google.cloud.security.privateca.v1.CertificateAuthority.name] of the [CertificateAuthority][google.cloud.security.privateca.v1.CertificateAuthority] to
   * get.
   * </pre>
   *
   * <code>string name = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
