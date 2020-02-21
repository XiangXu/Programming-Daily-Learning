# Git Useful Commands

**Revert file**  
```
git checkout [filename]
```

**How to revert command git commit -m 'message'**  
```
git reset --hard [commit] (origin)
```

**How to revert git push**  
```
git reset --hard [commit]
git push -- force
```

**When you want to fix an emergency bug but you alredy have your local code updated**  
```
git stash
git pop
```

**Delete local branch**  
```
git branch -d <local-branchname>
```

**Delete remote branch**  
```
git push origin :<remote-branchname>
```

**How to rename a branch name**  
```
git branch -m <new-branchname>
```

**Create and checkout to a new branch**  
```
git checkout -b <branch-name>
```

**Checkout**  
e.g. checkout tag v1.0 code.
```
git fetch --all
git tag
git checkout v1.0
```

**Push tag**  
e.g. push tag v1.1.0 with msg 
```
git tag -d v1.1.0
git tag -a v1.1.0 -m "my car and my ads"
git push origin :v1.1.0
git push origin v1.1.0
```

 Reference:

 https://www.jianshu.com/p/165d040b4a0d
