function Node (value=null){
    this.value = value;
    this.next = null;
    this.previus = null;
	this.index = 0;
}

function LinkedList(first = null){
    this.first=null;
    this.last=null;

    if(first){
        this.first = new Node(first);
        this.first.next = this.first;
        this.first.index = 0;
    
        this.last = this.first;
        this.first.previus = this.last;
        
        this.last.next = this.first;
        this.last.previus = this.first;
    }

    /**
     * Añade un valor al final de la lista.
     * @param {any} value Valor que se agregará.
     * @return {any} El ultimo valor en la lista.
     */
    this.add = function(value){
        if (this.first == null) {/**Si no existe ningun valor.*/
			this.first = new Node(value);
            this.first.next = this.first;
            this.first.index = 0;

			this.last = this.first;
			this.first.previus = this.last;
			
			this.last.next = this.first;
            this.last.previus = this.first;
            	
		}else {
            var newNode = new Node(value);
            newNode.index = this.last.index + 1;
			var preLast = this.last;  
			
			this.last = newNode;
            preLast.next = this.last;
            
			this.last.next = this.first;
            this.last.previus = preLast;
            this.first.previus = this.last;
        }
        return this.last.value;
    }

    /**
     * @return {int} La cantidad de elmentos dentro del la lista.
     */
    this.length = function(){
        var result = 1;
		
		if (this.first == null) {/**Lista vacía.*/
			return 0;
		}else {
			var current = this.first;
			while(current.next.index != this.first.index) {
				result++;
				current = current.next;
			}
		}
		
		return result;
    }

    /**
	 * Busca el nodo que se encuentra en el índice especificado.
	 * @param {int} index Número de índice.
	 * @return El nodo del indice especificado.
	 */
    this.getNode = function(index=0){
        if(!this.first) {
			throw "Lista vacía";
		}
		
		if(index >= this.length() || index < 0) {
			throw "Índice fuera de rango.";
		}
		
		if(index == 0) {
			return this.first;
		}
		
		if(index == this.length()-1) {
			return this.last;
		}
		
		var current = this.first;
		
		for (let i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current;
    }

    /**
	 * Busca el valor que se encuentra en el nodo especificado.
	 * @param {int} index Índice del nodo.
	 * @return El valor dentro del nodo.
	 */
    this.get = function(index=0){
        return this.getNode(index).value;
    }
    
    /**
	 * Elimina un nodo de la lista.
	 * @param {int} index Índice del nodo a eliminar.
	 * @return El nodo que ha sido eliminado de la lista.
	 */
    this.remove = function(index = this.length()-1){
        var current = new Node();
		var updateIndexes = function(index = 0){ /**Actualiza los índices de cada nodo desde el nodo especificado.*/
			var current = this.getNode(index);
			while(current.next.index != this.first.index) {
                let newIndex = current++;
                current.next.index = newIndex;
                current = current.next;
			}
        }
		if(index == 0) {
			if (this.length() == 1) {
				this.first=null;
				return false;
				
			}else {
                this.first.next.index = 0;
				this.first.next.previus = this.last;
				this.first=this.first.next;
				this.last.next = this.first;
				return this.first.value;
				
			}
		}
		
		if(index == (this.length())-1) {
			this.last = this.last.previus;
			this.last.next = this.first;
			return this.last.value;
		}
		
        current = this.getNode(index);
        current.next.index = current.index;
		current.next.previus = current.previus;
        current.previus.next = current.next;
		
		return current.value;
	}
	
	this.toString = function(tab=0){
		var tabs = "\t".repeat(tab);
		var result = `${tabs}[\n`;
		var current = this.first;
		while (current.next.index != this.first.index){
			result += `${tabs}\t${current.value.toString(tab+1)},\n`;
			current = current.next;
		}
		result += `${tabs}\t${this.last.value.toString(tab+1)}\n`;
		result += `${tabs}]`;

		return result;
	}

}