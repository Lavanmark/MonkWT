package monkWT.model.levels;

@SuppressWarnings("rawtypes")
public class TileXY implements Comparable{
		
	public int x;
	public int y;
	
	public TileXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileXY other = (TileXY) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		if(o == this)
			return 0;
		TileXY other = (TileXY)o;
		if(this.y == other.y){
			if(this.x == other.x)
				return 0;
			if(this.x > other.x)
				return 1;
			else
				return -1;
		}
		if(this.y > other.y)
			return 1;
		return -1;
	}
}