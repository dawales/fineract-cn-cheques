/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.mifos.cheque.listener;

import io.mifos.cheque.AbstractChequeTest;
import io.mifos.cheque.api.v1.EventConstants;
import io.mifos.core.lang.config.TenantHeaderFilter;
import io.mifos.core.test.listener.EventRecorder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ChequeEventListener {

  private final Logger logger;
  private final EventRecorder eventRecorder;

  public ChequeEventListener(@Qualifier(AbstractChequeTest.TEST_LOGGER) final Logger logger,
                             final EventRecorder eventRecorder) {
    super();
    this.logger = logger;
    this.eventRecorder = eventRecorder;
  }

  @JmsListener(
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_ISSUE_CHEQUES,
      subscription = EventConstants.DESTINATION
  )
  public void onIssueCheques(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                             final String payload) {
    this.logger.debug("Cheques issued.");
    this.eventRecorder.event(tenant, EventConstants.ISSUE_CHEQUES, payload, String.class);
  }

  @JmsListener(
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_CHEQUE_TRANSACTION,
      subscription = EventConstants.DESTINATION
  )
  public void onChequeTransaction(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                                  final String payload) {
    this.logger.debug("Cheque transaction processed.");
    this.eventRecorder.event(tenant, EventConstants.CHEQUE_TRANSACTION, payload, String.class);
  }

  @JmsListener(
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_CHEQUE_TRANSACTION_APPROVED,
      subscription = EventConstants.DESTINATION
  )
  public void onChequeApproved(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                               final String payload) {
    this.logger.debug("Cheque transaction approved.");
    this.eventRecorder.event(tenant, EventConstants.CHEQUE_TRANSACTION_APPROVED, payload, String.class);
  }

  @JmsListener(
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_CHEQUE_TRANSACTION_CANCELED,
      subscription = EventConstants.DESTINATION
  )
  public void onChequeCanceled(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                               final String payload) {
    this.logger.debug("Cheque transaction canceled.");
    this.eventRecorder.event(tenant, EventConstants.CHEQUE_TRANSACTION_CANCELED, payload, String.class);
  }
}
