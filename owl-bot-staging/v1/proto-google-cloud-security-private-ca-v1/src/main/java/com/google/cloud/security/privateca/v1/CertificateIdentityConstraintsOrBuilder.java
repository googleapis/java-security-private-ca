// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/security/privateca/v1/resources.proto

package com.google.cloud.security.privateca.v1;

public interface CertificateIdentityConstraintsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.security.privateca.v1.CertificateIdentityConstraints)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Optional. A CEL expression that may be used to validate the resolved X.509
   * Subject and/or Subject Alternative Name before a certificate is signed. To
   * see the full allowed syntax and some examples, see
   * https://cloud.google.com/certificate-authority-service/docs/using-cel
   * </pre>
   *
   * <code>.google.type.Expr cel_expression = 1 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return Whether the celExpression field is set.
   */
  boolean hasCelExpression();
  /**
   * <pre>
   * Optional. A CEL expression that may be used to validate the resolved X.509
   * Subject and/or Subject Alternative Name before a certificate is signed. To
   * see the full allowed syntax and some examples, see
   * https://cloud.google.com/certificate-authority-service/docs/using-cel
   * </pre>
   *
   * <code>.google.type.Expr cel_expression = 1 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The celExpression.
   */
  com.google.type.Expr getCelExpression();
  /**
   * <pre>
   * Optional. A CEL expression that may be used to validate the resolved X.509
   * Subject and/or Subject Alternative Name before a certificate is signed. To
   * see the full allowed syntax and some examples, see
   * https://cloud.google.com/certificate-authority-service/docs/using-cel
   * </pre>
   *
   * <code>.google.type.Expr cel_expression = 1 [(.google.api.field_behavior) = OPTIONAL];</code>
   */
  com.google.type.ExprOrBuilder getCelExpressionOrBuilder();

  /**
   * <pre>
   * Required. If this is true, the
   * [Subject][google.cloud.security.privateca.v1.Subject] field may be copied
   * from a certificate request into the signed certificate. Otherwise, the
   * requested [Subject][google.cloud.security.privateca.v1.Subject] will be
   * discarded.
   * </pre>
   *
   * <code>optional bool allow_subject_passthrough = 2 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return Whether the allowSubjectPassthrough field is set.
   */
  boolean hasAllowSubjectPassthrough();
  /**
   * <pre>
   * Required. If this is true, the
   * [Subject][google.cloud.security.privateca.v1.Subject] field may be copied
   * from a certificate request into the signed certificate. Otherwise, the
   * requested [Subject][google.cloud.security.privateca.v1.Subject] will be
   * discarded.
   * </pre>
   *
   * <code>optional bool allow_subject_passthrough = 2 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return The allowSubjectPassthrough.
   */
  boolean getAllowSubjectPassthrough();

  /**
   * <pre>
   * Required. If this is true, the
   * [SubjectAltNames][google.cloud.security.privateca.v1.SubjectAltNames]
   * extension may be copied from a certificate request into the signed
   * certificate. Otherwise, the requested
   * [SubjectAltNames][google.cloud.security.privateca.v1.SubjectAltNames] will
   * be discarded.
   * </pre>
   *
   * <code>optional bool allow_subject_alt_names_passthrough = 3 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return Whether the allowSubjectAltNamesPassthrough field is set.
   */
  boolean hasAllowSubjectAltNamesPassthrough();
  /**
   * <pre>
   * Required. If this is true, the
   * [SubjectAltNames][google.cloud.security.privateca.v1.SubjectAltNames]
   * extension may be copied from a certificate request into the signed
   * certificate. Otherwise, the requested
   * [SubjectAltNames][google.cloud.security.privateca.v1.SubjectAltNames] will
   * be discarded.
   * </pre>
   *
   * <code>optional bool allow_subject_alt_names_passthrough = 3 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return The allowSubjectAltNamesPassthrough.
   */
  boolean getAllowSubjectAltNamesPassthrough();
}
