package com.server.protocol;

import org.junit.Assert;
import org.junit.Test;

public class ProtocolHandlerTest {

	@Test
	public void sendAValidProtocolToBuild() {
		String request = "5:Test";
		Protocol protocolExpected = new Protocol(5, "Test", true);
		Protocol protocolActual = ProtocolHandler.buildProtocolBaseOnRequest(request);
		Assert.assertTrue(protocolActual.equals(protocolExpected));
	}

	@Test
	public void sendAValidWithWhiteSpacesProtocolToBuild() {
		String request = "5:Test with whitespace";
		Protocol protocolExpected = new Protocol(5, "Test%20with%20whitespace", true);
		Protocol protocolActual = ProtocolHandler.buildProtocolBaseOnRequest(request);
		Assert.assertTrue(protocolActual.equals(protocolExpected));
		
	}

	@Test
	public void sendAnInvalidProtocolToBuild() {
		String request = "Test:5";
		Protocol protocolExpected = buildInvalidProtocol();
		Protocol protocolActual = ProtocolHandler.buildProtocolBaseOnRequest(request);
		Assert.assertTrue(protocolActual.equals(protocolExpected));
	}

	@Test
	public void sendAEmptyProtocolToBuild() {
		String request = "";
		Protocol protocolExpected = buildInvalidProtocol();
		Protocol protocolActual = ProtocolHandler.buildProtocolBaseOnRequest(request);
		Assert.assertTrue(protocolActual.equals(protocolExpected));
	}

	private Protocol buildInvalidProtocol() {
		Protocol protocol = new Protocol();
		protocol.setValid(false);
		return protocol;
	}

	
}
