package openhex.game.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openhex.util.ListenerType;
import openhex.util.MultiNotifier;
import openhex.vec.fin.VectorAS;

public class Board implements IBoard, MultiNotifier<BoardListener> {
	
	public static enum BoardListenerTypes implements ListenerType {
		LOCK, CHANGE;
		
		
	}
	
	public static final Long LISTENERTYPE_LOCK = 0L;
	public static final Long LISTENERTYPE_CHANGE = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(Board.class);
	
	private Map<VectorAS, HexTile> tiles;
	private boolean lock = false;
	
	private Map<ListenerType, Set<BoardListener>> listeners;
	
	public Board() {
		tiles = new HashMap<>();
		listeners = new HashMap<>();
	}
	
	@Override
	public void addTile(HexTile tile) {
		tiles.put(tile.getPosition(), tile);
	}
	
	@Override
	public HexTile getTile(VectorAS pos) {
		return tiles.get(pos);
	}
	
	@Override
	public void removeTile(VectorAS pos) {
		tiles.remove(pos);
	}

	@Override
	public Collection<HexTile> getTiles() {
		return tiles.values();
	}

	@Override
	public void lock() {
		LOG.info("Locking the board...");
		
		lock = true;
		for(BoardListener l : getSet(BoardListenerTypes.LOCK)) {
			LOG.info("Notifying {}", l);
			((BoardLockListener)l).onLock(this);
		}
	}

	@Override
	public void unlock() {
		LOG.info("Unlocking the board...");
		
		lock = false;
		for(BoardListener l : getSet(BoardListenerTypes.CHANGE)) {
			((BoardLockListener)l).onUnlock(this);
		}
	}

	@Override
	public void setLock(boolean lock) {
		if(lock)	lock();
		else		unlock();
	}

	@Override
	public boolean isLocked() {
		return lock;
	}

	@Override
	public void addListener(BoardListener listener) {
		ListenerType type = getListenerType(listener);
		LOG.trace("Adding {} of type {}", listener, type);
		if(type != null) {
			getSet(type).add(listener);
		}
	}

	@Override
	public void removeListener(BoardListener listener) {
		ListenerType type = getListenerType(listener);
		if(type != null) {
			getSet(type).remove(listener);
		}
	}

	@Override
	public boolean isListening(BoardListener listener) {
		ListenerType type = getListenerType(listener);
		if(type != null) {
			return getSet(type).contains(listener);
		}
		return false;
	}

	private Set<BoardListener> getSet(ListenerType type) {
		Set<BoardListener> set = listeners.get(type);
		if(set == null) {
			LOG.debug("No set found for {}. Creating new one", type);
			set = new HashSet<>();
			listeners.put(type, set);
		}
		return set;
	}
	
	@Override
	public ListenerType getListenerType(BoardListener listener) {
		if(listener instanceof BoardLockListener) {
			return BoardListenerTypes.LOCK;
		}
		if(listener instanceof BoardChangeListener) {
			return BoardListenerTypes.CHANGE;
		}
		LOG.warn("{} is not supported!", listener);
		return null;
	}
	
	/*
	 * Returns a duplicate of the board for AI purposes, does NOT copy listeners.
	 */
	public Board getDuplicate() {
		Board b = new Board();
		
		for (HexTile tile : tiles.values()) {
			HexTile t = new HexTile(tile.position, tile.getResourceDescriptor());
			t.setOwner(tile.getOwner());
			b.addTile(t);
		}
		
		return b;
	}

	@Override
	public Map<VectorAS, HexTile> getTileMap() {
		return tiles;
	}
}
