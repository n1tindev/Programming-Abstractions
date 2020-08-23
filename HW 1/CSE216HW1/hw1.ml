(* Nitin Dev *)
(* NDEV *)
(* 112298641 *)


(* 1.1 Part 1 *)
let rec pow x n =     (* rec --> needed for recurssion*)
    if n = 0 then 1		(* anything to the power of 0 is 1 *)
    else x * pow x (n - 1);;

(* 1.1 Part 2 *)
let rec float_pow x n =   (* n is a positive integer, x is a float*)
  if n = 0 then 1.0
  else x *. float_pow x (n - 1);;


(* 1.2 *)
let rec compress = function
  | q :: (w :: _ as e) -> if q = w then compress e else q :: compress e
  | smaller -> smaller;;


(* 1.3 *) 
let rec remove_if lst f = match lst with 
| [] -> []
| h :: t -> if (f h) = true then remove_if t f
            else h :: remove_if t f ;;


(* 1.4 *)
let slice list a b =
  let rec get n = function
    | [] -> []
    | head ::tail -> if a > b then [] else if n = 0 then [] else head :: get (n-1)tail
  in
  let rec remove n = function
    | [] -> []
    | head ::tail as l -> if n = 0 then l else remove (n-1)tail
  in
  get (b - a) (remove a list);;


(* 1.5 *)
let rec equivsHelper fn lst = match lst with
  | [] -> []
  | h :: t -> if (fn h) then h :: (equivsHelper fn t)
              else (equivsHelper fn t);;

let rec listComparison lst1 lst2 = match lst1 with 
  | [] -> lst2
  | h :: t -> let x = (remove_if lst2 ((=)h)) in (listComparison t x);;

let rec equivs fn lst = match lst with 
  | [] -> [[]]
  | h :: t -> let x = (equivsHelper (fn h) lst) in 
              let y = (listComparison x t) in 
              match y with
              | [] -> [x]
              | hd::tl -> x :: (equivs fn y);;


(* 1.6 *)
let primeCheck input =
  let input = max input (-input ) in
  let rec divideCheck div =
    div * div > input || (input mod div <> 0 && divideCheck (div + 1)) in
  divideCheck 2;;

let rec possiblePrimeNumbers num1 num2 =
  if num1 > num2 then [] else
    let remainder = possiblePrimeNumbers (num1 + 1) num2 in
    if primeCheck num1 then num1 :: remainder else remainder;;

let goldbachpair mainInput =
  let rec aux x =
    if primeCheck x && primeCheck (mainInput - x) then (x, mainInput-x)
    else aux (x+1) in
  aux 2;;


(* 1.7 *)       
let rec equiv_on input1 input2 list =
  match list with
  | [] -> true
  | head::tail -> if (input1 head) = (input2 head) then equiv_on input1 input2 tail
                  else false;;


(* 1.8 *)
let rec pairwisefilter cmp given_list = 
  match given_list with
  | [] -> []
  | head::snd::tail -> let new_tail = pairwisefilter cmp tail in
           (cmp head snd) :: new_tail    
  | head::[] -> [head];;


(* 1.9 *)                        
let rec polynomial a = fun n ->
match a with
| [] -> 0
| (b, c) :: tail -> let remaining = polynomial tail in
    let x = (float_of_int n) ** (float_of_int c) |> int_of_float in
    (b * x) + (remaining n);;


(* 1.10 *)
let rec powerSetHelper fn lst = match lst with 
  | [] -> []
  | h :: t -> (fn h) :: (powerSetHelper fn t);;

let rec powerset = function
  | [] -> [[]]
  | head :: tail -> 
      let finalList = powerset tail in
      finalList @ powerSetHelper (fun s -> head :: s) finalList;;