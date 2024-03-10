package udp;

import java.util.logging.Logger;

import udp.core.UdpSocket;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

	public static void main(String[] args) {
		try {
			var socket = new UdpSocket(CommonConstant.DEFAULT_PORT);
			LOGGER.info("Logs from your program will appear here!");
			while (true) {
				var receivedData = socket.receivePacket();
				var address = receivedData.getLeft();
				var data = receivedData.getRight();

				socket.sendResponse(address, data);
			}
		} catch (Exception e) {
			LOGGER.info("Exception: %s%n".formatted(e.getMessage()));
		}
	}
}
