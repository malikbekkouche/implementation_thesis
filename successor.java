public final Node successor(K key){
		while(true){
			List<Node> nodes=new List();
			List<Operation> ops=new List();
			Node lastLeft=root;
			Node n=root;
			while(!n.isLeaf()){  
				if(key<n.key){
					lastLeft=n;
					n=n.left;
					nodes=new List();
					nodes.add(lastLeft);
					ops=new List();
					ops.add(lastLeft.op);
				}else{
					n=n.right;
					nodes.add(n);
					ops.add(n.op);
				}
			}
			if(key<n.key){
				return n;
			}else{
				Node succ=lastLeft.right;
				while(!succ.isLeaf()){
					nodes.add(succ);
					ops.add(succ.op);
					succ=succ.left;
				}
				if(vlx(nodes,ops))
					if(key < succ.key)
						return succ;
					else // successor does not exist
						return null;
				else // the path has been updated
					continue; // re try operation
			}
		}
	}