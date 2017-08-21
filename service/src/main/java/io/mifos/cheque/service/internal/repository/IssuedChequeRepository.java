/*
 * Copyright 2017 Kuelap, Inc.
 *
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Kuelap, Inc and its suppliers, if any.
 * The intellectual and technical concepts contained herein
 * are proprietary to Kuelap, Inc and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * Kuelap, Inc.
 */
package io.mifos.cheque.service.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssuedChequeRepository extends JpaRepository<IssuedChequeEntity, Long> {

  Optional<IssuedChequeEntity> findByAccountIdentifier(final String accountIdentifier);
}
