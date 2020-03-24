package cn.ucmed.common.db.basic;

import lombok.Data;

@Data
public class ClientSessionCache extends Cache {

	private static final long serialVersionUID = -6565055643411482998L;
	private String sessionId;
	private ClientSession clientSession;

	@Override
	public String getKey() {
		return "session_id" +sessionId;
	}

}
