package org.five.nav.client.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class AuditRequest {

    String owner;

    String user;

    String message;

    String action;

}
