package com.server.protocol;

import java.util.Arrays;
import java.util.List;

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

	@Test
	public void getResponse() {
		String expected = "3:Dark Knight\nToy Story\nThe Avengers\n";
		List<String> titles = Arrays.asList("Dark Knight", "Toy Story", "The Avengers");
		String actual = ProtocolHandler.buildResponse(titles);

		Assert.assertEquals(expected, actual);
	}

	
}
