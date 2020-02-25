# Git Study

### Basic git command

**git init**: initializes a brand new Git repository and begains tracking an existing directory. It adds a hidden subfolder within the existing directory that houses the internal data structure required for version control.

**git clone**: creates a local copy of a project that already exists remotely. The clone includes all the project's file, history, and branches.

**git add**: stages a change. Git tracks changes to a developer's codebase, but it's necessary to stage and take a snapshot of the changes to include them in the project's history. This command performs staging, the first part of that two-step process. Any changes that are staged will become a part of the next snapshot and a part of the project's history. Staging and committing seperately gives developers complete control over the history of their project without changing how they code and work.

**git commit**: saves the snapshot to the project hisotry and completes the change-tracking process. In short, a commit functions like taking a photo. Anything that's been staged with **git add** will become part of the snapshot with **git commit**.

**git status**: shows the status of changes as untracked, modified, or staged.

**git branch**: show the branches being worked on locally. 

**git merge**: merges lines of development together. This command is typically used to combine changes made on two distinct branches. For example, a developer would merge when they want to combine changes from a feature branch into the master branch for deployment.

**git pull**: updates the local line of development with updates from its remote counterpart(对应物). Developers use this command if a teammate has made commits to branch on a remote, and they would like to reflect those changes in their local environment. 

**git push**: updates the remote repository with any commits made locally to a branch.

#### Git tags

Tagging is generally used to capture a point in history that is used for a marked version release(ie. v1.0.1). A tag is like a branch that doesn't change. Unlike branches,
tags, after being created, have no further history of commits. 

**git tag <tagname>**: create a lightweight tag.

**git tag -a v1.4**: create annotated tags.

e.g. push tag v1.1.0 with msg 
```
git tag -d v1.1.0
git tag -a v1.1.0 -m "my car and my ads"
git push origin :v1.1.0
git push origin v1.1.0
```

Git supports two different types of tags, annotated and lightweight tags. Lightweight tags and Annotated tags differ in the amount of accompanying meta data they store. A best practice is to consider Anomated tags as public, and Lightweight tags are private. Anomated tags store extra meta data such as: the tagger name, email, and date. Lightweight tags are essentially "bookmarks" to a commit, they are just a name and a pointer to commit, useful for creating quick links to relevant commits. 

Reference: 


https://www.atlassian.com/git/tutorials/inspecting-a-repository/git-tag

https://guides.github.com/introduction/git-handbook/

http://luchenqun.com/%E6%9C%AA%E5%88%86%E7%B1%BB/git-tips/
