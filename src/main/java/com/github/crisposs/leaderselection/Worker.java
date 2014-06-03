package com.github.crisposs.leaderselection;

import java.net.UnknownHostException;
import java.util.Random;

import abs.api.Actor;
import abs.api.Reference;
import abs.api.ReferenceFactory;

public class Worker extends AbstractNode implements Actor, Node, LeaderSelector {

	private static final long serialVersionUID = 1L;

	static final Reference MASTER = ReferenceFactory.DEFAULT
			.create("master@http://localhost:7777");

	Random random = new Random(System.currentTimeMillis());
	int leader = -1;

	public Worker(int n) throws UnknownHostException {
		super(n);
		start();
	}

	public Integer selectLeader(Integer m) {
		leader = random.nextInt(m);
		logger.info("selected {} for input {}", leader, m);
		self.send(MASTER, new Vote(getId(), leader));
		return leader;
		// Reference mRef =
		// Reference.from("abs://master@http://localhost:7777");
		// Master mNode =
		// (Master)(object(Reference.from("abs://master@http://localhost:7777")));
		// nodeActor.send(mRef, () -> mNode.put(number,leader));

	}
}
