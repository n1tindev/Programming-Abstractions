(* Nitin Dev *)
(* NDEV *)
(* 112298641 *)

type bool_expr =      (* given method in problem *)
  | Lit of string
  | Not of bool_expr
  | And of bool_expr * bool_expr
  | Or of bool_expr * bool_expr;;

let rec evaluate a valueOfA b valueOfB = function
  | Lit x -> if x = a then valueOfA
             else valueOfB
  | Not e -> not(evaluate a valueOfA b valueOfB e)
  | And(e1, e2) -> evaluate a valueOfA b valueOfB e1 && evaluate a valueOfA b valueOfB e2
  | Or(e1, e2) -> evaluate a valueOfA b valueOfB e1 || evaluate a valueOfA b valueOfB e2;;

let truth_table a b expression =
  [(true, true, evaluate a true b true expression);
   (true, false, evaluate a true b false expression);
   (false, true, evaluate a false b true expression);
   (false, false, evaluate a false b false expression)];;
   