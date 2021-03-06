package openhex.game.board;

import openhex.es.ResourceDescriptor;
import openhex.game.board.owner.Ownable;
import openhex.game.board.owner.Owner;
import openhex.vec.fin.VectorAS;

/**
 * 
 * @author MisterCavespider
 *
 */
public class HexTile implements Ownable {
	
	private Owner owner;
	private HexTile[] neighbours = new HexTile[6];
	protected VectorAS position;
	protected ResourceDescriptor resDesc;
	
	public HexTile(VectorAS position, ResourceDescriptor resDesc) {
		this.position = position;
		this.resDesc = resDesc;
		for (HexTile tile: neighbours) {
			tile = null;
		}
	}
	
	public HexTile() {
		this(new VectorAS(0, 0, 0), new ResourceDescriptor(true));
	}

	public VectorAS getPosition() {
		return position;
	}

	public void setPosition(VectorAS position) {
		this.position = position;
	}
	
	public ResourceDescriptor getResourceDescriptor() {
		return resDesc;
	}

	@Override
	public Owner getOwner() {
		return owner;
	}

	@Override
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public HexTile getNeighbour(int n) {
		return neighbours[n];
	}
	
	public HexTile[] getNeighbours() {
		return neighbours;
	}
	
	public void setNeighbour(int n, HexTile t) {
		neighbours[n] = t;
	}
	
}
