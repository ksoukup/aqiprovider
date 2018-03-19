package com.fdm.commands;

import java.io.Serializable;

import com.fdm.model.User;

public interface WriteCommand {
	void write(User user);
}
