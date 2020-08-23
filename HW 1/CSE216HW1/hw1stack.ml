(* Nitin Dev *)
(* NDEV *)
(* 112298641 *)


type stack = Node of int list;;

let start input = 
  input([]);;
  
let push n nodePointer fn = match nodePointer with
  | nodePointer -> fn(n :: nodePointer);;

let pop elementRemove fn = match elementRemove with
  | [] -> fn([])
  | head :: tail -> fn(tail);;

let add stk fn = match stk with 
  | [] -> fn([])
  | [e1] -> fn(stk)
  | e1 :: (e2 :: e3) -> fn((e1 + e2) :: e3);;

let mult stk fn = match stk with 
  | [] -> fn([])
  | e1 :: [] -> fn(stk)
  | e1 :: (e2 :: e3) -> fn((e1 * e2) :: e3);;

let clone copy fn = match copy with 
  | [] -> []
  | head :: tail -> fn(head :: copy);;

let rec kpopHelper element stackList fn = match stackList with
  | [] -> fn([])
  | head :: tail -> if element < 0 then tail
                    else kpopHelper (element - 1) tail fn;;

let kpop element numPop fn = match numPop with
  | [] -> []
  | head :: tail -> fn(kpopHelper (head) tail fn);;
                    
let halt stopStk = match stopStk with
  stopStk -> stopStk;;