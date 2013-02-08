package chord;

import java.net.InetSocketAddress;

import interfaces.ChordObjectStorage;

public class ChordObjectStorageImpl implements ChordObjectStorage {

    public ChordObjectStorageImpl(int lol) {

    }

    @Override
    public void createGroup(int port) {
        // TODO Auto-generated method stub

    }

    @Override
    public void joinGroup(InetSocketAddress knownPeer, int port) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public InetSocketAddress getChordName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void leaveGroup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void put(String name, Object object) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object get(String name) {
        // TODO Auto-generated method stub
        return null;
    }



}
