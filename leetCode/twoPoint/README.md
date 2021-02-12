# Two pointer

A template for substring problems with two pointer and HashMap.
See example in [Problem076](./Problem076.cpp).

```c++
int findSubstring(std::string s){
    std::unorder_map<int, int> mp(128,0);
    int counter; // check whether the substring is valid
    int res; // the target result

    for() { /* initialize the hash map here */ }

    for(int begin=0, end=0; end<s.size(); end ++){

        if(mp[s[end]] ?){  /* modify counter here */ }

        while(/* counter condition */){

             /* update res here if finding minimum*/

            if(mp[s[begin]]++ ?){ /*modify counter here*/ }

            //increase begin to make it invalid/valid again
            begin ++;
        }
    }
    return res;
  }
```