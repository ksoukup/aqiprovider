package com.fdm.commands;

import com.fdm.model.User;

public interface ReadCommand {
	User readuser(String username);
}
