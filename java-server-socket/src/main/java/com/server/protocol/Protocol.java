package com.server.protocol;

public class Protocol {

	private int queryLength;
	private String query;
	private boolean valid;

	public Protocol() {
	}

	public Protocol(int queryLength, String query, boolean valid) {
		this.queryLength = queryLength;
		this.query = query;
		this.valid = valid;
	}

	public int getQueryLength() {
		return queryLength;
	}
	public void setQueryLength(int queryLength) {
		this.queryLength = queryLength;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + queryLength;
		result = prime * result + (valid ? 1231 : 1237);
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
		Protocol other = (Protocol) obj;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (queryLength != other.queryLength)
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}
	
}
