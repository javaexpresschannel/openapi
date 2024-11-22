package com.javaexpress.exceptions;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ErrorData", description = "Schema to hold error response information")
public class ErrorData {

	@Schema(description = "Status code in the response")
	private String status;

	private String code;
	private String title;

	@Schema(description = "Details Error Message Information")
	private String detail;

	@Schema(description = "Time representing when the error happened")
	private LocalDateTime localDateTime;
}
