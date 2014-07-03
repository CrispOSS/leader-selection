package com.github.crisposs.leaderselection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import abs.api.Actor;
import abs.api.Reference;
import abs.api.ReferenceFactory;

public class Master extends AbstractNode implements Actor, Node, VoteCollector {

	private static final long serialVersionUID = 1L;

	private List<Integer> nodes;
	private final int workers;

	public Master(int workers) throws Exception {
		super(0);
		this.workers = workers;
		this.nodes = new ArrayList<>(workers);
		start();
	}

	@Override
	public int getPort() {
		return 7777;
	}

	@Override
	public String getName() {
		return "master";
	}

	@Override
	public Object collect(Vote v) {
		logger.info("received vote: {}", v);
		nodes.add(v.getSelectedLeader());
		return v.getSelectedLeader();
	}

	private int count_max() {
		Set<Integer> keys = new HashSet<Integer>();
		int max = 0;
		for (Integer integer : keys) {
			if (max < Collections.frequency(nodes, integer)) {
				max = integer;
			}
		}
		return max;
	}

	public void run_alg() {
		Set<Future<Integer>> workFutures = new HashSet<>();
		for (int i = 0; i < workers; i++) {
			Reference nRef = getNodeReference(i);
			// Worker nNode = (Worker) object(nRef);
			// Runnable msg = () -> nNode.selectLeader(new
			// Integer(workers));
			Future<Integer> r = self.send(nRef, new Integer(workers));
			workFutures.add(r);
			logger.info("msg sent to node: {}", nRef);
		}

		workFutures.forEach(f -> {
			try {
				Integer result = f.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		workFutures.clear();

		show();

	}

	public void show() {
		logger.info("Leader= {}", count_max());
	}

	protected Reference getNodeReference(int n) {
		int port = DEFAULT_PORT_START + n;
		return ReferenceFactory.DEFAULT.create("node-" + n + "@http://localhost:" + port);
	}
}
