package chord;

import java.net.InetSocketAddress;
import java.util.*;

import services.ChordHelpers;
import services.DDistThread;
import interfaces.ChordObjectStorage;

public class ChordObjectStorageImpl extends DDistThread implements ChordObjectStorage {

    private boolean _isJoining;
    private boolean _isConnected;
    private boolean _wasConnected;
    private int _port;
    private int _myKey;
    private InetSocketAddress _myName;
    private InetSocketAddress _succ;
    private InetSocketAddress _pred;
    private InetSocketAddress _connectedAt;

    private Map<String, Object> _localStore;

    private Object _connectedLock;


    public ChordObjectStorageImpl(int expectedLifeSpan) {
        super(expectedLifeSpan);
        _isConnected = false;
        _wasConnected = false;
        _connectedLock = new Object();
        _localStore = Collections.synchronizedMap(new HashMap<String, Object>());
    }

    public void createGroup(int port) {
        synchronized(_connectedLock) {
            if (_wasConnected) {
                System.err.println(getName() + " says: Cannot connect twice!");
                return;
            }
            _wasConnected = true;
        }

        _isJoining = false;
        _port = port;
        _myName = ChordHelpers.getMyName(_port);
        _myKey = ChordHelpers.keyOfObject(_myName);
        start();
    }

    public void joinGroup(InetSocketAddress knownPeer, int port) {
        synchronized(_connectedLock) {
            if (_wasConnected) {
                // Someone is trying to connect again. This is not
                // allowed. Let's just write an error. Casting an
                // exception would have been nicer.
                System.err.println(getName() + " says: Cannot connect twice!");
                return;
            } 
            _wasConnected = true;
        }

        _isJoining = true;
        _port = port;
        _connectedAt = knownPeer;
        _myName = ChordHelpers.getMyName(port);
        _myKey = ChordHelpers.keyOfObject(_myName);
        start();
    }

    public boolean isConnected() {
        synchronized (_connectedLock) {
            return _isConnected;
        }
    }

    public InetSocketAddress getChordName() {
        return _myName;
    }

    public void leaveGroup() {
        synchronized(_connectedLock) {
            _isConnected = false;
        }
    }
    
    public InetSocketAddress succ() {
        return _succ;
    }

    public InetSocketAddress pred() {
        return _pred;
    }

    public InetSocketAddress lookup(int key) {
        return _myName;
    }

    public void put(String name, Object object) {

    }

    public Object get(String name) {
        return null;
    }

    
    public void run() {
        
    }


}
