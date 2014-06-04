package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

public class ModifierInstance
{


	private Modifier modifier;
	private int level;
	
	public Modifier getModifier()
	{
		return modifier;
	}

	public void setModifier(Modifier modifier)
	{
		this.modifier = modifier;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public ModifierInstance(Modifier modifier, int level)
	{
		this.modifier = modifier;
		this.level = level;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModifierInstance other = (ModifierInstance) obj;
		if (level != other.level)
			return false;
		if (modifier == null)
		{
			if (other.modifier != null)
				return false;
		}
		else if (!modifier.equals(other.modifier))
			return false;
		return true;
	}
}
