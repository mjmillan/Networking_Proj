package networking.project.game.network.packets;

import networking.project.game.utils.NetCodes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by nick on 3/28/17.
 */
public abstract class Packet implements NetCodes {

    /**
     * The binary data of the packet to send. Each packet received is broken down
     * in child classes, to proper member variables.
     *
     * Therefore, this field is only really used when constructing a packet to send.
     */
    protected byte[] data;

    /**
     * Takes an input buffer from a received from a DatagramPacket and,
     * depending on the child class, reads the appropriate data into the class'
     * member fields.
     * @param data The input byte array
     */
    public abstract void decompose(byte[] data);

    /**
     * Takes what the class has defined in its member variables, and packs
     * them into the byte array, making it ready for sending.
     */
    public abstract void compose();

    /**
     * Sends a packet over a server socket to a given IP and port.
     * @param serverSocket The server socket.
     * @param inet The IP address of the recipient
     * @param port The port of the recipient
     */
    public void send(DatagramSocket serverSocket, InetAddress inet, int port) {
        try {
            serverSocket.send(new DatagramPacket(data, data.length, inet, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void send(DatagramSocket server, DatagramPacket clientPack)
    {
        send(server, clientPack.getAddress(), clientPack.getPort());
    }


    public static Packet determinePacket(byte[] data)
    {
        Packet toReturn;
        switch (data[0]) // Depending on the identifier byte
        {
            case CONN_REQ:
            case CONN_ACK:
            case CONN_DISC:
            case CONN_MSG:
                toReturn = new ConnectionPacket();
                break;
            case GAME_START:
                toReturn = new GameStartPacket();
                break;
            case GAME_PROJ_UPDATE:
                toReturn = new ProjectileUpdatePacket();
                break;
            case GAME_KILL_UPDATE:
                toReturn = new KillPacket();
                break;
            case GAME_PLAYER_UPDATE:
            	toReturn = new PlayerUpdatePacket();
            	break;
            default:
                toReturn = null;
                break;
        }

        if (toReturn != null)
            toReturn.decompose(data);

        return toReturn;
    }
}
