package udp.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.logging.Logger;

import udp.CommonConstant;
import udp.util.Pair;

/**
 * @author byeongcheol.kim
 */
public class UdpSocket {
	private static final Logger LOGGER = Logger.getLogger(UdpSocket.class.getSimpleName());

	private final DatagramSocket datagramSocket;

	public UdpSocket(int port) throws SocketException {
		this.datagramSocket = new DatagramSocket(port);
		Runtime.getRuntime().addShutdownHook(new Thread(datagramSocket::close));
	}

	public Pair<SocketAddress, String> receivePacket() {
		try {
			var buf = new byte[CommonConstant.PACKET_SIZE];
			var packet = new DatagramPacket(buf, CommonConstant.PACKET_SIZE);
			datagramSocket.receive(packet);

			return Pair.of(packet.getSocketAddress(), new String(buf));
		} catch (IOException e) {
			LOGGER.info("IOException: %s%n".formatted(e.getMessage()));
			return null;
		}
	}

	public void sendResponse(SocketAddress address, String response) {
		try {
			var bufResponse = new byte[512];
			var packetResponse = new DatagramPacket(bufResponse, bufResponse.length, address);
			datagramSocket.send(packetResponse);
		} catch (IOException e) {
			LOGGER.info("IOException: %s%n".formatted(e.getMessage()));
		}
	}
}
